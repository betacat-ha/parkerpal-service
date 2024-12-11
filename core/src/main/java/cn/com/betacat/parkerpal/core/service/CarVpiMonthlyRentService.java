package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.CarVpiMonthlyRent;
import cn.com.betacat.parkerpal.common.domain.query.CarVpiMonthlyRentQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;

/**
 * 车辆管理-vip月租车登记管理表(CarVpiMonthlyRent)表服务接口
 *
 * @author zoey
 * @since 2024-08-14 13:03:03
 */
public interface CarVpiMonthlyRentService extends IService<CarVpiMonthlyRent> {

    /**
     * 车辆管理-vip月租车登记管理表-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(CarVpiMonthlyRentQuery query);

    /**
     * 根据内地车牌查询是否存在VIP月租车
     *
     * @param mainlandLicensePlates
     */
    CarVpiMonthlyRent getByLicensePlates(String mainlandLicensePlates);

    /**
     * 车辆管理-vip月租车登记管理表-新增或修改
     *
     * @param entity
     */
    void createOrUpdate(CarVpiMonthlyRent entity);
}
