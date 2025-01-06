package cn.com.betacat.parkerpal.apicontracts.service.mixin;

public interface ChargeRulesService {

    Integer getFreeDuration();

    String getTollStandard();

    String getFeeCap();

    String getMonthlyInternalCar();

    String getMonthlyEnterpriseCar();

    String getMonthlyInternalCarNoMachinery();

    String getMonthlyExternalCarMachinery();

    String getUserId();

}
