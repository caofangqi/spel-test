package com.caofangqi.test.speltest;

import com.caofangqi.test.speltest.utils.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author caofangqi created at 2020/3/20 3:16 PM
 */
public class EvaluationContextTest {


    public static void main(String[] a) throws NoSuchMethodException {
        //上下文环境
        StandardEvaluationContext context = new StandardEvaluationContext();
        Object [] args = new Object[]{"hello","h","star","hey","fuck"};
        //设置变量
        context.setVariable("args",args);
        //注册函数
        context.registerFunction("ituc", StringUtils.class.getMethod("getInitialToUpperCase",String.class));

        ExpressionParser parser = new SpelExpressionParser();
        //解析表达式 调用上面注册的函数 传入 变量 args 的第二个元素
        Expression expression = parser.parseExpression("#ituc(#args[2])");
        System.out.println("首字母大写:"+expression.getValue(context));
    }




}
