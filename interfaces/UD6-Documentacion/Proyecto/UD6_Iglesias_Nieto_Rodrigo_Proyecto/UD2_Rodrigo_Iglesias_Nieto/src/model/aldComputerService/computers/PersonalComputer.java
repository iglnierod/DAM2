/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.aldComputerService.computers;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class PersonalComputer extends Computer {

    private String graphicsCard;
    private String processor;
    private boolean networkCard;

    public PersonalComputer(String serialNumber, String brand, String model, ComputerType computerType) {
        super(serialNumber, brand, model, computerType);
    }

    public PersonalComputer(String graphicsCard, String processor, boolean networkCard,
            String serialNumber, String brand, String model, ComputerType computerType) {
        super(serialNumber, brand, model, computerType);
        this.graphicsCard = graphicsCard;
        this.processor = processor;
        this.networkCard = networkCard;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public boolean isNetworkCard() {
        return networkCard;
    }

    public void setNetworkCard(boolean networkCard) {
        this.networkCard = networkCard;
    }

}
