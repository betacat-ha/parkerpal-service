package cn.com.betacat.parkerpal.apicontracts.service;

import cn.com.betacat.parkerpal.domain.entity.MonthlyInsurancePayment;
import cn.com.betacat.parkerpal.domain.entity.RevenueStatistics;
import cn.com.betacat.parkerpal.domain.query.MonthlyInsurancePaymentQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 月保-缴费记录(MonthlyInsurancePayment)表服务接口
 *
 * @author
 * @since 2024-08-16 13:23:21
 */
public interface MonthlyInsurancePaymentService extends IService<MonthlyInsurancePayment> {

    /**
     * 月保-缴费记录-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(MonthlyInsurancePaymentQuery query);

    /**
     * 查询车辆月保记录
     *
     * @param plates
     * @return
     */
    MonthlyInsurancePayment getEntity(String plates);

    /**
     * 查询车辆月保状态
     *
     * @param plates
     * @return false：过保，true：在保
     */
    boolean getCarStatus(String plates);

    /**
     * 新增或更新月保记录
     *
     * @param entity
     */
    void createOrUpdate(MonthlyInsurancePayment entity);

    RevenueStatistics getStatistics(String userId);
}
