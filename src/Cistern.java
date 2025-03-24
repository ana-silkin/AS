public class Cistern extends ActiveElement {
    private int cisternVolume;

    /**
     * Default constructor
     *
     * @param p
     */
    public Cistern(Position p) {
        super(p,3);
    }

    public void setVolume(int volume) { this.cisternVolume = volume; }
    public int getVolume() { return cisternVolume; }
}
