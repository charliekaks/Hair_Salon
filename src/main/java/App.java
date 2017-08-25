import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Map;

import org.apache.velocity.Template;

import java.util.ArrayList;
public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/stylist", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylist", Stylist.all());
            model.put("template", "templates/.vtl");
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

        get("/", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists",Stylist.all());
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

        get("/client", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("stylists", Stylist.all());
            model.put("template", "templates/client.vtl");
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());


        post("/stylist", (request, response)->{
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("stylistName");
            int phone = Integer.parseInt(request.queryParams("phoneNumber"));
            Stylist myStylist = new Stylist(name, phone);
            myStylist.save();
            model.put("template", "templates/success.vtl");
            return new ModelAndView(model, layout);
        },new VelocityTemplateEngine());

        post("/client", (request, response)->{
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("clientName");
            int phone = Integer.parseInt(request.queryParams("phoneNumber"));
            Stylist stylistid = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
            Client myClient = new Client(name, phone, stylistid.getId());
            myClient.save();
            model.put("stylist", stylistid);
            model.put("template", "templates/success.vtl"); 
            return new ModelAndView(model, layout); 
        },new VelocityTemplateEngine());

        
    }
}
