package com.firefly.fireflysns.modelimpl;

import com.firefly.fireflysns.common.Logger;
import com.firefly.fireflysns.model.FirestoreRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;

/**
 * @author firefly2.kim
 * @since 19. 8. 30
 */
public class FirestoreRepositoryImpl implements FirestoreRepository {

    private static final String TAG = FirestoreRepositoryImpl.class.getSimpleName();
    private static final String COLLECTION_NAME_USERS = "users";

    @Override
    public Single<Boolean> addUserIfNotExists(FirebaseUser firebaseUser) {
        return Single.create(emitter -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(COLLECTION_NAME_USERS).document(firebaseUser.getUid()).get()
                    .addOnSuccessListener(snapShotData -> {
                        if (snapShotData.exists()) {
                            Logger.d(TAG, "uid is already exists. : " + firebaseUser.getUid());
                            emitter.onSuccess(true);
                        } else {
                            Logger.d(TAG, "there is no uid. need to add data");
                            addUserToFirestore(firebaseUser, db, emitter);
                        }
                    });
        });
    }

    private void addUserToFirestore(FirebaseUser firebaseUser, FirebaseFirestore db,
                                    SingleEmitter<Boolean> emitter) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("name", firebaseUser.getDisplayName());
        obj.put("email", firebaseUser.getEmail());

        String photoUrl = firebaseUser.getPhotoUrl() == null ? "" : firebaseUser.getPhotoUrl().toString();
        obj.put("photo_url", photoUrl);
        obj.put("timestamp_created", FieldValue.serverTimestamp());

        db.collection(COLLECTION_NAME_USERS)
                .document(firebaseUser.getUid())
                .set(obj)
                .addOnSuccessListener(unused -> emitter.onSuccess(true))
                .addOnFailureListener(thr -> emitter.onError(thr));
    }
}
