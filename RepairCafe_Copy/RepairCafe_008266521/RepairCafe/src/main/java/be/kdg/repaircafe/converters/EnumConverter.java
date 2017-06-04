package be.kdg.repaircafe.converters;

import be.kdg.repaircafemodel.dom.repairs.RepairDetails;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("enumConverter")
public class EnumConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            switch (value) {
                case "Fixed":
                    return RepairDetails.PriceModel.FIXED;
                case "Per Hour":
                    return RepairDetails.PriceModel.PER_HOUR;
            }
            System.out.println("EnumConverter: successfully converted");
        } catch (ConverterException ex) {
            System.out.println("enumConverter: error: " + ex.getMessage());
            FacesMessage msg = new FacesMessage("Could not convert Enum");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("EnumConverter: value to string"+value.toString());
        return value.toString();
    }

}
