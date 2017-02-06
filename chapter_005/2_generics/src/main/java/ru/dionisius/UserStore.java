package ru.dionisius;

/**
 * Created by Dionisius on 04.02.2017.
 */
public class UserStore extends ABase implements IStore {

    final int size = 100;
    private final SimpleArray array = new SimpleArray(this.size);

    @Override
    public void add(final ABase object) {
        this.array.add(object);
    }

    @Override
    public void update(final ABase oldObject, final ABase newObject) {
        this.array.update(oldObject, newObject);
    }

    @Override
    public void delete(final ABase object) {
        this.array.delete(object);
    }

    public boolean exist(final ABase object) {
        boolean result = false;
        if (object == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.array.get(i) == null) {
                    result = true;
                    break;
                }
            }
        }
        for (int i = 0; i < this.size; i++) {
            if (this.array.get(i) == object) {
                result = true;
                break;
            }
        }
        return result;
    }
}
