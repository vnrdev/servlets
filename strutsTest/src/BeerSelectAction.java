package com.example.web;

//Model imports
import com.example.model.*;
import java.util.*;

//Struts imports
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

// Servlet imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeerSelectAction extends Action {
  public ActionForward execute(ActionMapping mapping, ActionForm form,
          HttpServletRequest request,
          HttpServletResponse response) {
      // Cast the form to the application-specif c form
      BeerSelectForm myForm = (BeerSelectForm) form;
      // Process the business logic
      BeerExpert be = new BeerExpert();
      List result = be.getBrands(myForm.getColor());
      // Forward to the Results view
      // (and store the data in the request-scope)
      request.setAttribute(“styles”, result);
      return mapping.f ndForward(“show_results”);
    }
}
