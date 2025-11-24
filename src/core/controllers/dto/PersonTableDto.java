package core.controllers.dto;

public class PersonTableDto {

    private final long id;
    private final String fullName;
    private final String role;
    private final String detail;
    private final int extraCount;

    public PersonTableDto(long id, String fullName, String role, String detail, int extraCount) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
        this.detail = detail;
        this.extraCount = extraCount;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getDetail() {
        return detail;
    }

    public int getExtraCount() {
        return extraCount;
    }
}
