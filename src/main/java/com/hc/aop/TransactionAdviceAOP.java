package com.hc.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/4/1 11:33
 * @description：全局事务切面AOP
 * @modified By：
 * @version:
 */
@Aspect
@Configuration
public class TransactionAdviceAOP {

    /**
     * 定义切点
     */
    private static final String AOP_POINT_CUT = "execution(* com.hc.modules.service..*.*(..))";

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public TransactionInterceptor txAdvice(){
        //事务匹配规则，按照需要事务方法名模糊匹配
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        //设置声明式事务，适合于"查"
        RuleBasedTransactionAttribute ruleSelect = new RuleBasedTransactionAttribute();
        //设置事务隔离级别为ISOLATION_READ_COMMITTED,保证一个事务修改的数据提交后才能被另外一个事务读取
        ruleSelect.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //设置事务传播行为为PROPAGATION_SUPPORTS,如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行
        ruleSelect.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        //设置回滚的异常为RuntimeException
        ruleSelect.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(RuntimeException.class)));
        //设置事务只读，不允许该事务里有其他非读事务
        ruleSelect.setReadOnly(true);

        //设置声明式事务,适合于"增改删"
        RuleBasedTransactionAttribute ruleChange = new RuleBasedTransactionAttribute();
        //设置事务隔离级别为ISOLATION_READ_COMMITTED,保证一个事务修改的数据提交后才能被另外一个事务读取
        ruleChange.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //设置事务传播行为为PROPAGATION_REQUIRED,如果存在一个事务，加入当前事务。如果没有事务,则新建一个事务
        ruleChange.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //设置回滚的异常为RuntimeException
        ruleChange.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(RuntimeException.class)));


        Map<String, TransactionAttribute> map = new HashMap<>();
        map.put("find*", ruleSelect);
        map.put("query*", ruleSelect);
        map.put("select*", ruleSelect);
        map.put("get*", ruleSelect);
        map.put("create*", ruleChange);
        map.put("insert*", ruleChange);
        map.put("batch*", ruleChange);
        map.put("update*", ruleChange);
        map.put("change*", ruleChange);
        map.put("delete*", ruleChange);
        map.put("remove*", ruleChange);
        map.put("lesson*", ruleChange);
        source.setNameMap(map);
        //配置事务拦截器
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor(platformTransactionManager, source);
        return transactionInterceptor;
    }


    /**
     * 利用AspectJExpressionPointcut配置切面
     */
    @Bean
    public Advisor txAdviceAdvisor(){
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        //切点拦截的路径
        aspectJExpressionPointcut.setExpression(AOP_POINT_CUT);
        return new DefaultPointcutAdvisor(aspectJExpressionPointcut, txAdvice());

    }

}
