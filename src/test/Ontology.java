package test;
    import java.util.*;  
    import com.hp.hpl.jena.rdf.model.*;  
    import com.hp.hpl.jena.ontology.*;  
    public class Ontology  
    {  
    public static void main(String[] args) {  
      // ???????????  
      OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);  
      ontModel.read("file:/home/fry/Documents/protege.owl"); // ???????????????  
      // ???????????????  
      for (Iterator i = ontModel.listClasses(); i.hasNext();) {  
       OntClass c = (OntClass) i.next();  
         
       if (!c.isAnon()) { // ?????????????  
        System.out.print("Class");  
          
        // ??????URI???????????????URI?????  
        Sdystem.out.println(c.getModel().getGraph().getPrefixMapping().shortForm(c.getURI()));   
        // ????????????????  
        for (Iterator it = c.listSuperClasses(); it.hasNext();)  
        {  
         OntClass sp = (OntClass) it.next();  
            String str = c.getModel().getGraph()  
            .getPrefixMapping().shortForm(c.getURI()) // ???URI  
            + "'s superClass is " ;  
         String strSP = sp.getURI();  
         try{ // ??????????URI?????  
          str = str + ":" + strSP.substring(strSP.indexOf('#')+1);  
          System.out.println("  Class" +str);  
         }  
         catch( Exception e ){  
         }  
        }  
        // ?????????????????  
        for (Iterator it = c.listSubClasses(); it.hasNext();)  
        {  
         System.out.print("  Class");  
         OntClass sb = (OntClass) it.next();  
         System.out.println(c.getModel().getGraph().getPrefixMapping()  
           .shortForm(c.getURI())  
           + "'s subClass is "  
           + sb.getModel().getGraph().getPrefixMapping()  
             .shortForm(sb.getURI()));  
        }  
          
        // ????????????????????????  
        for(Iterator ipp = c.listDeclaredProperties(); ipp.hasNext();)  
        {  
         OntProperty p = (OntProperty)ipp.next();  
          System.out.println("  associated property: " + p.getLocalName());  
        }  
       }  
      }  
        
    }  
    }  