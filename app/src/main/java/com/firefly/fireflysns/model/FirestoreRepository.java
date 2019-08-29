package com.firefly.fireflysns.model;

import com.google.firebase.auth.FirebaseUser;

import io.reactivex.Single;

/**
 * @author firefly2.kim
 * @since 19. 8. 30
 */
public interface FirestoreRepository {

    Single<Boolean> addUserIfNotExists(FirebaseUser firebaseUser);
}
