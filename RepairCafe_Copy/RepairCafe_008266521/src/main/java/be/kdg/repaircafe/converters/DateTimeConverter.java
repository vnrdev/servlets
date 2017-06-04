package be.kdg.repaircafe.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@FacesConverter("dateTimeConverter")
public class DateTimeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/mm/yyyy");
            DateTime theDate = dtfOut.parseDateTime(value);
            System.out.println("DateTimeConverter: successfully converted (getAsObject)");
            return theDate;
        } catch (ConverterException | IllegalArgumentException ex) {
            FacesMessage msg = new FacesMessage("Could not convert DateTime. (getAsObject)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("MM/dd/yyyy");
            DateTime theDate = (DateTime) value;
            System.out.println("DateTimeConverter: successfully converted (getAsString): "+dtfOut.print(theDate));
            return dtfOut.print(theDate);
        } catch (ConverterException | IllegalArgumentException ex) {
            FacesMessage msg = new FacesMessage("Could not convert DateTime. (getAsString)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
    }

}
