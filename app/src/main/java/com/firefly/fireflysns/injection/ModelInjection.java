package com.firefly.fireflysns.injection;

import com.firefly.fireflysns.model.FirestoreRepository;
import com.firefly.fireflysns.modelimpl.FirestoreRepositoryImpl;

/**
 * @author firefly2.kim
 * @since 19. 8. 25
 */
public class ModelInjection {

    private ModelInjection() {
        // No instance
    }

    public static FirestoreRepository provideFirestoreRepository() {
        return new FirestoreRepositoryImpl();
    }
}
