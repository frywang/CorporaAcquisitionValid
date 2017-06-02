package test;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import edu.stanford.smi.protege.model.Model;
import edu.stanford.smi.protegex.owl.
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.writer.rdfxml.rdfwriter.OWLModelWriter;

public class ReadAndWriteOWL {

 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  try {
   ReadAndWrite();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }

 }
 
    //??§ÕOWL???
 private static void ReadAndWrite() throws IOException
 {
  //?????
  String filePath="E:\\owlfile\\Family.owl";
   FileInputStream file= new FileInputStream(filePath);
        Reader in = new InputStreamReader(file,"UTF-8");
        OWLModel owlModel=null;
       
  try {
   
   owlModel = ProtegeOWL.createJenaOWLModelFromReader(in);
   OWLNamedClass destinationClass = owlModel.getOWLNamedClass("Family");
   owlModel.createOWLNamedSubclass("marrying", destinationClass);
   System.out.printf("AddOK!");
  }
  catch (Exception e)
  {
   
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
        //Model dodel=(Model)owlModel.getJenaModel();
        in.close();
 //§Õ??
    FileOutputStream outFile= new FileOutputStream(filePath);
    Writer out = new OutputStreamWriter(outFile,"UTF-8");
    OWLModelWriter omw = new OWLModelWriter(owlModel, owlModel.getTripleStoreModel().getActiveTripleStore(), out);
    omw.write();
    out.close();

  
 }

}