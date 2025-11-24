package core.controllers.dto;

public class PublisherTableDto {

    private final String nit;
    private final String name;
    private final String address;
    private final String managerName;
    private final int standQuantity;

    public PublisherTableDto(String nit, String name, String address, String managerName, int standQuantity) {
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.managerName = managerName;
        this.standQuantity = standQuantity;
    }

    public String getNit() {
        return nit;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getManagerName() {
        return managerName;
    }

    public int getStandQuantity() {
        return standQuantity;
    }
}
