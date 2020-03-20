package com.caofangqi.test.speltest;

import com.caofangqi.test.speltest.utils.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author caofangqi created at 2020/3/20 4:43 PM
 */
public class ParserContextTest {


    public static void main(String[] a) throws NoSuchMethodException {

        //上下文环境
        StandardEvaluationContext context = new StandardEvaluationContext();
        Object [] args = new Object[]{"hello","h","star","hey","fuck"};
        //设置变量
        context.setVariable("args",args);
        //注册函数
        context.registerFunction("ituc", StringUtils.class.getMethod("getInitialToUpperCase",String.class));

        ExpressionParser parser = new SpelExpressionParser();
        //解析表达式 加入 parserContext 之后就发现 将表达式当成字符串输出了
        Expression expression = parser.parseExpression("#ituc(#args[2])",new ParserContextImpl());
        System.out.println("首字母大写:"+expression.getValue(context));

        //解析表达式 加入 parserContext 之后就发现 将表达式当成字符串输出了
        Expression expression1 = parser.parseExpression("%{#ituc(#args[2])}%",new ParserContextImpl());
        System.out.println("首字母大写:"+expression1.getValue(context));
    }


    static class ParserContextImpl implements ParserContext{
        @Override
        public boolean isTemplate() {
            return true;
        }

        @Override
        public String getExpressionPrefix() {
            return "%{";
        }

        @Override
        public String getExpressionSuffix() {
            return "}%";
        }
    }
}
