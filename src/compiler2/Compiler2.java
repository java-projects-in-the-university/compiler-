/*
 */
package compiler2;

import javax.swing.*;
import java.util.*;
public class Compiler2 extends interfaces{

    public static interfaces gui;
    static Stack  stack=new Stack();
    static String postix="",store="";
   
    public static void TopostFix(String infix){
    char[] tocharacter=infix.toCharArray();
    gui.TableContent.append("        move\tToken\tStack\toutput"
    + "\n-----------------------------------------------------------------------------------------");
        int j=0;
        for(int i=0; i<tocharacter.length;i++ ){
               gui.TableContent.append("\n        "+(i+1)+"\t"+tocharacter[i]+"\t");
               j++;
             if(Character.isLetter(tocharacter[i])||Character.isDigit(tocharacter[i])||tocharacter[i]=='='){
                  postix+= tocharacter[i];
                  gui.TableContent.append("\t"+postix);       
             }else if(tocharacter[i] == '+' || tocharacter[i] == '-' || tocharacter[i] == '*' || tocharacter[i] == '/' || tocharacter[i]== '^'){
                       while(!stack.empty() && (!stack.peek().equals('(')) && (priority(tocharacter[i],(char) stack.peek())==true)){ 
                              store=postix+= stack.pop();
                       }
              stack.push(tocharacter[i]);
              gui.TableContent.append(stack.peek()+"\t"+postix);
                    } 
           else if(tocharacter[i]==')'){
                    while(!stack.empty() && !stack.peek().equals('(') ) {
		         postix += stack.pop();
                    }

                     stack.pop();
                     gui.TableContent.append("\t"+postix);
                }
           else if(tocharacter[i]=='('){
              stack.push(tocharacter[i]);
              gui.TableContent.append(stack.peek()+"\t"+postix);
           }   
      }   
        while(!stack.isEmpty()){
        postix += stack.pop();
        } 
     gui.TableContent.append("\n        "+(j+1)+"\tEmpty"+"\tEmpty\t"+postix );
    }
    
    public static int  operatorDegree(char operator ){
        int degree;
        if(operator=='^'){ return degree=3;}
        else if (operator=='*'||operator=='/'){return degree=2;}
        else  if(operator=='+'||operator=='-'){return degree=1;}
        else  
        return 0;
    }
    
    public static boolean priority( char operator,char operatorInStack){
        int priority1,priority2;
        priority1=operatorDegree(operator);
        priority2=operatorDegree(operatorInStack);
        return priority1<priority2||priority1==priority2;     
    }

    public static void main(String[] args) {
     
        gui = new interfaces();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}
