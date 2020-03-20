package com.caofangqi.test.speltest;

import com.caofangqi.test.speltest.entiry.Inventor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * @author caofangqi created at 2020/3/20 10:49 AM
 */
public class ExpressionEvaluation {

    public static void main(String[] args) {
        //创建一个 解析器
        ExpressionParser parser = new SpelExpressionParser();
        //解析表达式 获得表达式 对象
        Expression expression = parser.parseExpression("'hello world'");
        System.out.println("hello world:"+expression.getValue());

        //表达式里调用方法
        expression = parser.parseExpression("'hello world'.length()");
        System.out.println("hello world.length():"+expression.getValue());
        //表达式里调用方法
        expression = parser.parseExpression("'hello world'.concat('!')");
        System.out.println("hello world.concat('!'):"+expression.getValue());
        //通过表达式获取属性
        expression = parser.parseExpression("'hello world'.bytes");
        System.out.println("hello world.bytes:"+Arrays.toString((byte[]) expression.getValue()));
        //通过表达式 获取属性 的属性 支持及联
        expression = parser.parseExpression("'hello world'.bytes.length");
        System.out.println(expression.getValue());

        evaluationAgainstSpecificObjectInstance();
    }


    public static void evaluationAgainstSpecificObjectInstance(){
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856,7,9);

        Inventor tesla = new Inventor("Nikola Tesla",c.getTime(),"Serbian");
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(tesla);

        String name = (String) expression.getValue(context);
        System.out.println("name:"+name);

        String name2 = (String) expression.getValue(tesla);
        System.out.println("name2:"+name2);

        //布尔操作符 求值
        Expression expression1 = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = expression1.getValue(tesla,Boolean.class);
        System.out.println("result:"+result);

    }

}
