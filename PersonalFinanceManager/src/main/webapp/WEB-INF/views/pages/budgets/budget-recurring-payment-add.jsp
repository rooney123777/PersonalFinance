<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    $(document).ready(function () {
        $("#recurring-payment-add-form").validate({
            rules: {
                recurringPaymentAmount: {"required": true, "number": true, "min": 1},
                recurringPaymentPeriodsCount: {"required": true, "number": true, "min": 1},
                recurringPaymentName: {"required": true},
                recurringPaymentPeriodsMiss: {"required": true, "number": true}
            },
            messages: {
                recurringPaymentAmount: "Моля въведете сума.",
                recurringPaymentPeriodsCount: "Моля въведете брой периоди на плащането.",
                recurringPaymentName: "Моля въведете име на плащането."
            },
            errorPlacement: function (error, element) {

            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass("error");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass("error");
            }
        });
        $("#categories-select,#recurring-types-select").select2({"theme": "classic"});
        $('#recurringPaymentStartDate').flatpickr({
            'locale': 'bg',
            'mode': 'single',
            'enableTime': true,
            'defaultDate': 'today',
            'dateFormat': "Y-m-d",
            onChange: function (rawdate, altdate, FPOBJ) {
                FPOBJ.close();
            }
        });
    });
</script>
<form id="recurring-payment-add-form" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="row">
        <div class="col-6 col-md-6">
            <div class="row">
                <div class="col-12 col-md-12">
                    <div class="panel panel-success">
                        <div class='panel-heading'>Данни за плащане</div>
                        <div class="panel-body">
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Име на плащане</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-pencil" aria-hidden="true"></i></span>
                                    <input name="recurringPaymentName" type="text" class="form-control" placeholder="Име на плащане" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Сума за период</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-usd" aria-hidden="true"></i></span>
                                    <input name="recurringPaymentAmount" value="0" type="text" class="form-control" placeholder="Сума за период" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Пропускане през брой периоди</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-sort" aria-hidden="true"></i></span>
                                    <input name="recurringPaymentPeriodsMiss" type="text" value="0" class="form-control" placeholder="Пропускане през" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Начална дата на плащане</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                    <fmt:parseDate pattern="yyyy-MM-dd" value="${payment.getDate()}" var="parsedDate" />
                                    <fmt:formatDate value="${parsedDate}" var="date" pattern="yyyy-MM-dd" />
                                    <input readonly name="recurringPaymentPeriodStart" id="recurringPaymentStartDate" placeholder="Начална дата на повтарящо плащане" value="${date}" class="form-control" aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Брой периоди</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-sort" aria-hidden="true"></i></span>
                                    <input name="recurringPaymentPeriodsCount" type="text" value="0" class="form-control" placeholder="Брой периоди" aria-describedby="basic-addon1">
                                </div>
                            </div>
                                <div class="form-group ">
                                <label for="exampleFormControlInput1">Описание</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-pencil" aria-hidden="true"></i></span>
                                    <textarea class="form-control"  resize="false" placeholder="Описание" name="recurringPaymentDescription" aria-describedby="basic-addon1"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6 col-md-6">
            <div class="row">
                <div class="col-12 col-md-12">
                    <div class="panel panel-info">
                        <div class='panel-heading'>Настройки на плащане</div>
                        <div class="panel-body">
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Категория</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-bars" aria-hidden="true"></i></span>
                                    <select id="categories-select" name="recurringPaymentCategory">
                                        <c:forEach items="${categories}" var="element">
                                            <option value="${element.getId()}">${element.getName()}</option>
                                        </c:forEach>
                                    </select>
                                    <spring:url var = "categoriesAdd" value='/categories/add' />
                                    <div class="input-add-button">
                                        <a href="${categoriesAdd}" target="_blank"><i class="fa fa-plus-circle" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="exampleFormControlInput1">Плащането е</label>
                                <div class="input-group col-lg-12 col-md-12 col-sm-12">
                                    <span class="input-group-addon" id="basic-addon1"><i class="fa fa-bars" aria-hidden="true"></i></span>
                                    <select id="recurring-types-select" name="recurringPaymentRecurringType">
                                        <c:forEach items="${recTypes}" var="element">
                                            <option value="${element.getId()}">${element.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="btn-group">
        <button name="submit-button" class="btn btn-primary" type="submit" value="1">Запази</button>
        <button name="submit-button" class="btn btn-primary" type="submit" value="2">Запази и редактирай</button>
        <button name="submit-button" class="btn btn-primary" type="submit" value="3">Запази и Нов</button>
        <button type="reset" class="btn btn-primary">Изчистване на форма</button>
    </div>
</form>