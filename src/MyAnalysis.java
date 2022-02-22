import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.*;

/**
 * Some code that uses JavaSymbolSolver.
 */
public class MyAnalysis {
    /**
     private static class MethodNamePrinter extends VoidVisitorAdapter<Void>{
     public void visit(MethodDeclaration md, Void arg) {
     super.visit(md, arg);
     if ((md.getModifiers()).contains("default")) {
     System.out.println("Method Name Printed: " + md.getName());
     }
     }
     }
     **/


    private static void processNode(Node node, String scope) {
        if (node instanceof CompilationUnit || node instanceof ClassOrInterfaceDeclaration) {
            if (node instanceof ClassOrInterfaceDeclaration) {
                ClassOrInterfaceDeclaration cid = (ClassOrInterfaceDeclaration) node;
                scope = scope + cid.getName().toString() + '.';
            }
            for (Node child : node.getChildNodes()){
                processNode(child,scope);
            }
        } else if (node instanceof MethodDeclaration) {
            MethodDeclaration me = (MethodDeclaration) node;
            if (me.getModifiers().toString().contains("default")) {
                System.out.println(scope + "java");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        File sourceFile = new File("/Users/panjia/internship/cassandra-cassandra-3.11.10/test/distributed/org/apache/cassandra/distributed/impl/Instance.java");
        CompilationUnit cu = StaticJavaParser.parse(sourceFile);
        //System.out.println(cu.toString());
        String scope = new String();
        processNode(cu, scope);
        //VoidVisitor<Void> methodNameVisitor=new MethodNamePrinter();
        //methodNameVisitor.visit(cu,null);
    }
}
