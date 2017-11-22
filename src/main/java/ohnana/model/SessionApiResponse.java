package ohnana.model;

import ohnana.model.generic.ApiResponse;

public class SessionApiResponse {
    // To be deleted
    public static ApiResponse<Session> createSessionApiResponse(Session session) {
        return ApiResponse.createApiResponse(session);
    }
}


