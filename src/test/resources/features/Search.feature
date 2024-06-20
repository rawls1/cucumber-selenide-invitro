Feature: Поисковая система
  Background: Я на главной странице
    Given Открываем сайт "https://www.invitro.ru/"

  Scenario Outline: Поиск по коду анализа
    When Вводим код анализа в поле поиска "<analysisCode>"

    Examples:
      | analysisCode |
      | 1515         |

