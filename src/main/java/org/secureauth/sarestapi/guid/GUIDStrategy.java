package org.secureauth.sarestapi.guid;

import java.util.UUID;

public interface GUIDStrategy {

    UUID generateRequestID();
}
