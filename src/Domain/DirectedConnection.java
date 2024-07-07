package Domain;

public class DirectedConnection extends Connection{
    private RailwayStation start;
    private RailwayStation end;
    private final Connection wrappedConnection;
    public DirectedConnection(Connection con, RailwayStation start, RailwayStation end){
        super(con.getConnected().get(0), con.getConnected().get(1), con.getLength());
        coordinator = con.coordinator;
        this.start = start;
        this.end = end;
        wrappedConnection = con;
    }

    public void reverse() {
        var tmp = start;
        start = end;
        end = tmp;
    }

    @Override
    public boolean isOccupied() {
        return wrappedConnection.isOccupied();
    }

    @Override
    public void setOccupied(boolean occupied) {
        wrappedConnection.setOccupied(occupied);
    }

    public RailwayStation getStart() {
        return start;
    }

    public RailwayStation getEnd() {
        return end;
    }
}
