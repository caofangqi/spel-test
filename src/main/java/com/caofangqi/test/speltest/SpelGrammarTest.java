package com.caofangqi.test.speltest;

import com.caofangqi.test.speltest.utils.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author caofangqi created at 2020/3/20 4:58 PM
 */
public class SpelGrammarTest {

    public static void main(String[] a) throws NoSuchMethodException {
        //上下文环境
        StandardEvaluationContext context = new StandardEvaluationContext();
        Object [] args = new Object[]{1,2,3,4,6};
        //设置变量
        context.setVariable("args",args);

        ExpressionParser parser = new SpelExpressionParser();
        //表达式  加减乘除
        Expression expression = parser.parseExpression("#args[0]+#args[1]-#args[2]*#args[3]/#args[0]");
        System.out.println("加减乘除:"+expression.getValue(context));
        //表达式 求余
        expression = parser.parseExpression("#args[0]%#args[1]");
        System.out.println("求余%:"+expression.getValue(context));
        //表达式 求余 mod
        expression = parser.parseExpression("#args[0]mod#args[1]");
        System.out.println("求余mod:"+expression.getValue(context));
        //关系表达式 以及逻辑表达式
        expression = parser.parseExpression("#args[0]==#args[1]and#args[0]==#args[2]");
        System.out.println("关系表达式:"+expression.getValue(context));
        //关系表达式 between
        expression = parser.parseExpression("#args[0]between{1,3}");
        System.out.println("关系表达式between:"+expression.getValue(context));
        //关系表达式 以及逻辑表达式  三木运算符
        expression = parser.parseExpression("#args[0]==#args[1]and#args[0]==#args[2]?#args[1]!=#args[2]:#args[3]>#args[2]");
        System.out.println("关系表达式加上三目运算符:"+expression.getValue(context));
        //Elivis运算
        expression = parser.parseExpression("#args[0]!=#args[1]?:#args[1]!=#args[2]");
        System.out.println("Elivis运算:"+expression.getValue(context));
        //类相关表达式 获取 类对象 java.lang 下 直接类名 其他包下 需要全限定名
        expression = parser.parseExpression("T(String).getName()");
        System.out.println("类相关表达式 :"+expression.getValue(context));
        //instanceof 表达式
        expression = parser.parseExpression("#args[1] instanceof T(String)");
        System.out.println("instanceof 表达式:"+expression.getValue(context));


    }
}
