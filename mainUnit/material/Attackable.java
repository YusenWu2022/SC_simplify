package SC2_simplify.mainUnit.material;

public interface Attackable {
    public void hurt(double tmp);

    public int getX();

    public int getY();

    public void recover(double tmp);
    
    
}