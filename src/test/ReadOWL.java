package test;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;

import com.hp.hpl.jena.ontology.OntModel;

import com.hp.hpl.jena.ontology.OntModelSpec;

import com.hp.hpl.jena.rdf.model.ModelFactory;

import com.hp.hpl.jena.ontology.*;

 

public class ReadOWL {

 

       public static void main(String[] args) {

              // create the model and import owl file

              OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );

           try

           {

                  model.read(new FileInputStream("D://sars.owl"),"");

           }

           catch(IOException ioe)

           {

                  System.err.println(ioe.toString());

           }

           //the class number

           int j=0;

          //list classes

           for(Iterator i=model.listClasses();i.hasNext();)

           {

                  j++;

            OntClass c = (OntClass) i.next();

            String strClass=c.getModel().usePrefix(c.getURI());

            System.out.println(j+strClass.substring(1));

           

            //to list sub-classes for each class

            for(Iterator k=c.listSubClasses(true);k.hasNext();)

            {

                   System.out.print("  "+"hasSubClass");

                   OntClass subclass=(OntClass)k.next();

                   String strSubClass=subclass.getModel().usePrefix(subclass.getURI());

                   System.out.println(strSubClass.substring(1));

            }

            

            //list property for each class

            for(Iterator y=c.listDeclaredProperties(true);y.hasNext();)

            {

                  

                   OntProperty property=(OntProperty)y.next();

      

                   String strPropertyName=property.getModel().usePrefix(property.getURI());

                  

                   String strRange=property.getRange().toString();

                   String strRangeName=property.getModel().usePrefix(strRange);

                  

                   //show just the "has" Properties

                   if(strPropertyName.substring(1).substring(0,3).equals("has"))

                   {

                         

                          System.out.print("  ");

                       System.out.print(strPropertyName.substring(1));

                       System.out.println(strRangeName.substring(1));

                   }

                  

            }

           }

       };

}