final class Event {
    private final Action action;
    private long time;
    private final Entity entity;

    public Entity getEntity() {
        return entity;
    }

    public long getTime() {
        return time;
    }


    public Action getAction() {
        return action;
    }

    public Event(Action action, long time, Entity entity) {
        this.action = action;
        this.time = time;
        this.entity = entity;
    }
}
