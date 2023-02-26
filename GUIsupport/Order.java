package SC2_simplify.GUIsupport;

public class Order {
    public int type;
    public int kind;
    public int x;
    public int y;
    public int side;

    public Order(int type, int kind, int side, int x, int y) {
        this.type = type;
        this.kind = kind;
        this.x = x;
        this.y = y;
        this.side = side;
    };
}