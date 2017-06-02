package test;


import java.io.FileInputStream;

import java.io.IOException;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.OntClass;

import com.hp.hpl.jena.ontology.OntModel;

import com.hp.hpl.jena.ontology.OntModelSpec;

import com.hp.hpl.jena.rdf.model.ModelFactory;

import com.hp.hpl.jena.ontology.*;

public class Test {

	 // ����ʹ��OWL���Ե��ڴ�ģ��

	OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	
	ontModel.
	
    

ontModel.read("file:./food.owl"); // ��ȡ��ǰ·���µ��ļ�������ģ��

	// ����һ������Ϊģ����Animal��ĵȵȼ��࣬�����ע��

	OntClass cls = ontModel.createClass(":DongwuClass");

	cls.addComment("the EquivalentClass of Animal...", "EN");

	// ͨ��������URIȡ��ģ���е�Animal��

	OntClass oc = ontModel.

	getOntClass("http://www.owl-ontologies.com/marine.owl#Animal");

	oc.addEquivalentClass(cls); // ����ǰ����������ΪAnimal�ĵȼ���

	 

	// ������ʾģ���е��࣬�ڵ�����������ɸ��ֲ���

	for (Iterator i = ontModel.listClasses(); i.hasNext();) {

	OntClass c = (OntClass) i.next(); // ��������ǿ��ת��

	if (!c.isAnon()) { // ������������࣬���ӡ�������

	System.out.print("Class");

	// ��ȡ���URI������������ʱ��URI���˼򻯣��������ռ�ǰ׺ʡ�ԣ�

	System.out.println(c.getModel().getGraph().

	getPrefixMapping().shortForm(c.getURI()));

	                           

	// ����Animal��

	if (c.getLocalName().equals("Animal")) { // �����ǰ����Animal

	       System.out.println("  URI@" + c.getURI()); // �����������URI                 // ȡ�����ĵĵȼ��ಢ��ӡ

	       System.out.print("  Animal's EquivalentClass is "+

	c.getEquivalentClass());

	       // ����ȼ����ע��

	System.out.println(" [comments:" +

	c.getEquivalentClass().getComment("EN")+"]");

	}// ����Animal����

	      

	// ������ʾ��ǰ���ֱ�Ӹ���

	for (Iterator it = c.listSuperClasses(); it.hasNext();)

	{

	       OntClass sp = (OntClass) it.next();

	       String str = c.getModel().getGraph()

	                            .getPrefixMapping().shortForm(c.getURI()) // ��ȡURI

	                            + "'s superClass is " ;

	       String strSP = sp.getURI();

	       try{ // ��һ�ּ򻯴���URI�ķ���

	              str = str + ":" + strSP.substring(strSP.indexOf('#')+1);

	              System.out.println("  Class" +str);

	       }catch( Exception e ){}

	} // super class ends

	 

	// ������ʾ��ǰ���ֱ������

	for (Iterator it = c.listSubClasses(); it.hasNext();){

	System.out.print("  Class");

	OntClass sb = (OntClass) it.next();

	System.out.println(c.getModel().getGraph().getPrefixMapping()

	                                          .shortForm(c.getURI())+ "'s suberClass is "

	                                          + sb.getModel().getGraph().getPrefixMapping()

	                                          .shortForm(sb.getURI()));

	}// suber class ends

	// ������ʾ�뵱ǰ����ص���������

	for(Iterator ipp = c.listDeclaredProperties(); ipp.hasNext();){

	       OntProperty p = (OntProperty)ipp.next();

	       System.out.println("  associated property: " + p.getLocalName());

	       }// property ends

	}// anonymity ends

	else // ��������

	{}

	}// for ends
}
