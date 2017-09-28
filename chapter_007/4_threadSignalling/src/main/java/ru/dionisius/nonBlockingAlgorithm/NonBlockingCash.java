package ru.dionisius.nonBlockingAlgorithm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Created by Dionisius on 23.03.2017.
 * Thread safe storage class for model.
 */
public class NonBlockingCash {
    /**
     * Thread safe storage for model.
     */
    private final ConcurrentHashMap<Long, Model> map = new ConcurrentHashMap<>();

    /**
     * Adds the new specified model to the storage.
     * @param newModel the new specified model.
     */
    public void add(final Model newModel) {
        this.map.put(newModel.getId(), newModel);
    }

    /**
     * Updates old model with new model in the storage.
     * @param newModel new model to update the old model.
     */
    public void update(final Model newModel) {
        this.map.computeIfPresent(newModel.getId(), new BiFunction<Long, Model, Model>() {
            @Override
            public Model apply(Long id, Model oldModel) {
                if (oldModel.getVersion() == newModel.getVersion()) {
                    oldModel.setName(newModel.getName());
                } else {
                    throw new OptimisticException("version is incorrect!");
                }
                return oldModel;
            }
        });
    }

    /**
     * Deletes specified model from the storage.
     * @param model specified model that should be deleted.
     */
    public void delete(final Model model) {
        this.map.remove(model.getId());
    }
}
