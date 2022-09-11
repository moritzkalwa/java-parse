import java.util.List;

public class Info {
    public final Coordinate2d coord;
    public final short elevation;
    public final String id;
    public final boolean isTransferStation;
    public final short level;

    /* renamed from: name  reason: collision with root package name */
    public final String name;
    public final List<Net> nets;
    public final int omc;
    public final String parentId;
    public final String parentName;

    public Info(String id, String name2, String parentId, String parentName, int i, short s, short s2, boolean z, Coordinate2d coord, Object geometry, List<Net> nets) {
        this.id = id;
        this.name = name2;
        this.parentId = parentId;
        this.parentName = parentName;
        this.omc = i;
        this.level = s;
        this.elevation = s2;
        this.isTransferStation = z;
        this.coord = coord;
        this.nets = nets;
    }
}
