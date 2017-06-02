package test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.WrappedIterator;
import com.hp.hpl.jena.vocabulary.RDFS;





public class AddDelete { 
	
    public static void main(String[] args) {
        // create the model and import owl file
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
     try
     {
  	   m.read(new FileInputStream("/home/fry/Documents/protege.owl"),"");
     }
     catch(IOException ioe)
     {
            System.err.println(ioe.toString());
     }
 
  
  //???class??label
  OntClass cls = m.createClass("http://www.360iii.org/ontologies/fruit#OWLClass_dfe66630_50bb_4596_b79e_ab2e457d5a06");   
  cls.addLabel("????class1", "zh");
  
  OntClass randCls = m.createClass("http://www.360iii.org/ontologies/fruit#OWLClass_"+ Math.random());
  cls.addLabel("???????????class", "zh");
 
  //???class?????Equivalent??? 
  OntClass oc = m.getOntClass("http://www.360iii.org/ontologies/fruit#OWLClass_dfe66630_50bb_4596_b79e_ab2e297d5a06");
  oc.addEquivalentClass(cls); 
  
  //???individual??label
  Individual e1 = m.createIndividual("http://www.360iii.org/ontologies/fruit#OWLNamedIndividual_"+ Math.random(), cls);
  e1.addLabel("???????1", "zh");
  e1.addRDFType(cls);
  
  m.createIndividual("http://www.360iii.org/ontologies/fruit#OWLNamedIndividual_"+ Math.random(), cls).addLabel("???????2", "zh");

  //???ObjectProperty??label
  ObjectProperty p1 = m.createObjectProperty( "http://www.360iii.org/ontologies/fruit#OWLObjectProperty_"+ Math.random()); 
  p1.addLabel("???????????", "zh");
  DatatypeProperty p2 = m.createDatatypeProperty( "http://www.360iii.org/ontologies/fruit#OWLDataProperty_"+ Math.random()); 
  p2.addLabel("????????????", "zh");

  
  System.out.print("?????cls??????label???"+cls.getLabel("zh")+"\r\n"+"?????cls??URI???"+cls.getURI()+"\r\n\r\n");
  System.out.print("?????randCls??????label???"+randCls.getLabel("zh")+"\r\n"+"?????cls??URI???"+randCls.getURI()+"\r\n\r\n");
  System.out.print("??????e1??label???"+e1.getLabel("zh")+"\r\n"+"??????e1??URIl???"+e1.getURI()+"\r\n\r\n");
  System.out.print("??????????p1??label???"+p1.getLabel("zh")+"\r\n"+"??????e1??URIl???"+p1.getURI()+"\r\n\r\n");
  
  
    }
}

//Property d = m.createProperty( "http://example.com/double"); 
//Property i = m.createProperty( "http://example.com/int"); 
//m.add( m.createResource("http://example.com/A"), d, 
//m.createTypedLiteral(-1.3) ); 
//m.add( m.createResource("http://example.com/A"), i, 
//m.createTypedLiteral(-3) ); 
//m.add( m.createResource("http://example.com/B"), d, 
//m.createTypedLiteral(1.5) ); 
//m.add( m.createResource("http://example.com/B"), i, 
//m.createTypedLiteral(5) ); 
//m.add( m.createResource("http://example.com/C"), d, 
//m.createTypedLiteral(1.7) ); 
//m.add( m.createResource("http://example.com/C"), i, "7" ); 
  
  
//  m.add( m.createResource("http://www.360iii.org/ontologies/fruit#OWLNamedIndividual_cb84b0aa_3218_4f5a_bf48_49398a2a99ae"), d, 
//  m.createTypedLiteral(-1.3) ); 
//  m.add( m.createResource("http://example.com/A"), i, 
//  m.createTypedLiteral(-3) ); 
//  m.add( m.createResource("http://example.com/B"), d, 
//  m.createTypedLiteral(1.5) ); 
//  m.add( m.createResource("http://example.com/B"), i, 
//  m.createTypedLiteral(5) ); 
//  m.add( m.createResource("http://example.com/C"), d, 
//  m.createTypedLiteral(1.7) ); 
//  m.add( m.createResource("http://example.com/C"), i, "7" ); 
  
  
  
  

  
  
//  String testURI = "http://www.360iii.org/ontologies/fruit#OWLClass_31e25bf3_1a91_49c5_a2e3_cb7825fa139e"; 
//  
//  //????Resource
//  Resource test = m.createResource(testURI);  
//  test.addProperty(RDFS.label, m.createLiteral("??????", "zh")); 
//  test.addProperty(RDFS.label, m.createLiteral("test", "en")); 
//
//              m.createResource()  
//                  .addProperty(RDFS.label, m.createLiteral("Fuck??Why so hard!", "zh"))
//                  .addProperty(RDFS.label, m.createLiteral("you are crazy", "en"));   
//              
//  
//  
//
//  
// 
//   
//
//   
//  
////  public static void main(String args[]) 
////  { 
////   String filename = "D:\\Fruits-lite.owl"; 
////    
////   // Create an empty model 
////   Model model = ModelFactory.createDefaultModel(); 
////      
////   // Use the FileManager to find the input file 
////   InputStream in = FileManager.get().open(filename); 
////  
////   if (in == null) 
////    throw new IllegalArgumentException("File: "+filename+" not found"); 
////  
////   // Read the RDF/XML file 
////   model.read(in, null); 
////  
//  
//  
//  
//  
//  final Query q = QueryFactory.create(qry); 
//  final QueryExecution qexec = QueryExecutionFactory.create(q, m); 
//  final List<QuerySolution> retval = WrappedIterator.create( 
//    qexec.execSelect()).toList(); 
//  for (final QuerySolution qs : retval) { 
//   System.out.println(qs); 
//  } 
// } 
// 
//// static URL fUrl = J4SConnectionTest.class 
////   .getResource("./J4SStatementTest.ttl"); 
//
// 
// 
////static String qry = "SELECT ?s ?o  WHERE {?s <http://www.360iii.org/ontologies/fruit#OWLDataProperty_01cb6c89_7e09_4382_b617_255891d3d34121212> ?o }";
//static String qry = "SELECT ?s   WHERE {?s a <http://www.360iii.org/ontologies/fruit#OWLClass_31e25bf3_1a91_49c5_a2e3_cb7825fa139e> .}";
// 
//// static String qry = "SELECT  * " 
////   + "   WHERE" 
////   + "     { { { { ?fooTable <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://example.com/jdbc4sparql#fooTable> ." 
////   + "             ?fooTable <http://example.com/jdbc4sparql#IntCol> ?v_b3f2fd82_c102_3c4d_baed_5958c464a424 ." 
////   + "             ?fooTable <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?v_599dcce2_998a_3b40_b1e3_8e8c6006cb0a ." 
////   + "             ?fooTable <http://example.com/jdbc4sparql#StringCol> ?v_f20fd591_2dfa_3a09_b81b_7c6f31cc3159" 
////   + "           }" 
////   + "           OPTIONAL" 
////   + "             { ?fooTable <http://example.com/jdbc4sparql#NullableStringCol> ?v_2ca911d9_9e97_3d80_aaae_d6347f341e4e}" 
////   + "           OPTIONAL" 
////   + "             { ?fooTable <http://example.com/jdbc4sparql#NullableIntCol> ?v_ce84b044_b71d_37a4_bc63_462bd432993c}" 
////   + "         }" 
////   + "    BIND((?v_b3f2fd82_c102_3c4d_baed_5958c464a424) AS ?IntCol)" 
////   + "        BIND((?v_ce84b044_b71d_37a4_bc63_462bd432993c) AS ?NullableIntCol)" 
////   + "        BIND((?v_599dcce2_998a_3b40_b1e3_8e8c6006cb0a) AS ?type)" 
////   + "        BIND((?v_f20fd591_2dfa_3a09_b81b_7c6f31cc3159) AS ?StringCol)" 
////   + "        BIND((?v_2ca911d9_9e97_3d80_aaae_d6347f341e4e) AS ?NullableStringCol)" 
////   + "       }" + "     }"; 
//}
