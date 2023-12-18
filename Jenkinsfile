#!/usr/bin/env groovy

// load helpers
library 'jenkins-pipeline-libs'

node('radius-win-java11') {

  def gh_cid = scm.getUserRemoteConfigs()[0].getCredentialsId()
  def scmUrl = scm.getUserRemoteConfigs()[0].getUrl()
  boolean is_master = isMaster()

  properties([

    // dont keep builds in Jenkins.
    buildDiscarder( logRotator(artifactDaysToKeepStr: '',
      artifactNumToKeepStr: '3',
      daysToKeepStr: '',
      numToKeepStr: '30') ),

    // set url for diff links to gh
    [ $class: 'GithubProjectProperty',
      displayName: '',
      projectUrlStr: "${getGitHubURL(scmUrl)}" ]
  ])

  try {
    stage('Checkout') {
      deleteDir()
      checkout scm
    }

     stage('Build') {
          def build_cmd = "call gradlew.bat --info --refresh-dependencies clean build"
          if (is_master) {
            currentBuild.displayName = "#${env.BUILD_NUMBER} - v${getVersion()}"
          }
          bat build_cmd
     }

    // invoke any steps specific to the master(release) branch
    stage('Tag & Push to Artifactory') {
      if (is_master) {
        // depends on GIT_USERNAME & GIT_PASSWORD env vars
        applyTag(gh_cid, "v${getVersion()}", scmUrl)
      }

      deployToArtifactory()
    }
  }
  catch (e) {
    // set status to failed
    currentBuild.result = "FAILED"

    // still need to throw so Jenkins will log error
    throw e
  }
  finally {
    stage('Archive, Clean & Notify') {
      def artifacts = 'build/libs/*.jar'
      def recipients = '#radius_notify'
      def status = currentBuild.result ?: 'SUCCESS'
      def msg = "${env.JOB_NAME} - <${env.BUILD_URL}|#${env.BUILD_NUMBER}" +
        (is_master ? " - v${getVersion()}" : "") +
        "> - ${status}\n\n${getChangeString()}"

      // save artifacts in Jenkins
      archiveArtifacts artifacts

      //send via library
      notifySlack {
        buildStatus = status
        channel = recipients
        message = msg
      }

      deleteDir()
    }
  }
}

// fetches version from file
def getVersion(){
  def matches = readFile('gradle.properties') =~ /currentVersion *= *["']?([0-9\.\-a-zA-Z]*)/
  matches ? matches[0][1] : null
}

// fetch global gradle identifier for Jenkins
// TODO move to global lib
def getGradleId() {
  'gradle33'
}

boolean isMaster(){
  ("${env.BRANCH_NAME}" =~ /^(master|hotfix-.+)$/)
}

boolean isDevelop(){
  ("${env.BRANCH_NAME}" == "develop")
}

boolean isPR(){
  ("${env.BRANCH_NAME}" =~ /^(PR-[0-9]+)$/)
}

// deploys artifacts & build info to Artifactory
//TODO - this and archive step should ref same files
//TODO - use build retention to auto-delete old builds on develop only
void deployToArtifactory(){
  def server = Artifactory.server 'on-prem-artifactory'
  def gradle = Artifactory.newGradleBuild()

  def repo = ""
  if ( isMaster() ) {
    repo = "radius-release-local"
  } else if ( (isDevelop() || isPR()) && ( "${getVersion()}" =~ /^(.+SNAPSHOT)$/ ) ) {
    repo = "radius-snapshot-local"
  } else {
    // only deploy for master/hotfix/develop
    bat "echo Not a PR or Master, skipping deployment."
    return
  }

  // define where it will be deployed
  gradle.deployer server: server, repo: repo

  // if com.jfrog.artifactory plugin is already used in script
  //gradle.usesPlugin = true

  // use the wrapper and not the 'master' jenkins gradle.
  gradle.useWrapper = true

  //deploy maven descriptors
  gradle.deployer.deployMavenDescriptors = true

  //use gradle defined in main config
  gradle.tool = getGradleId()

  def build_info = gradle.run buildFile: 'build.gradle', tasks: 'clean artifactoryPublish'
  build_info.number = getVersion()

  def uploadSpec = """{
    "files": [
      {
        "pattern": "build/libs/*.jar",
        "target": "${repo}/org/secureauth/sarestapi/saRestApi/${getVersion()}/"
      }
  ]
  }"""

  server.publishBuildInfo(build_info)
  server.upload spec: uploadSpec, failNoOp: true
}
