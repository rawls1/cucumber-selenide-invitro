Feature: [API] Проверка бэкэнд запросов
  Scenario Outline: Проверка запроса для различных городов
    When Отправляем GET запрос с параметром CODE "<cityCode>"
    Then Код ответа должен быть 200
    And Тело ответа поле "city" должно содержать "<expectedCity>"

    Examples:
      | cityCode | expectedCity |
      | bajmak   | Баймак       |
      | moscow   | Москва       |
      | london   | Лондон       |
