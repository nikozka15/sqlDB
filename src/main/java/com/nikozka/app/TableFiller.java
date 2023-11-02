package com.nikozka.app;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableFiller {
    private static final Logger log = LoggerFactory.getLogger(TableFiller.class);
    Connection connection;

    public TableFiller(Connection connection) {
        this.connection = connection;
    }

    public void fillTales() {
        try {
            fillNumberTable();
            fillStreetTable();
            fillCityTable();
            fillAddressTable();
            fillShopTable();
            fillProductCategoryTable();
            log.info("Base tables are filled");
        } catch (SQLException e) {
            log.error("Base tables not filled", e);
        }
    }

    private void fillNumberTable() throws SQLException {
        String sql = "INSERT INTO number (value) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 1; i <= 20; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.executeUpdate();
            }
        }
    }

    private void fillStreetTable() throws SQLException {
        String sql = "INSERT INTO street (name) VALUES (?)";
        String[] streetNames = {"Шевченка", "Лесі Українки", "Гагаріна", "Бандери", "Садова",
                "Героїв Майдану", "Грушевського", "Сагайдачного", "Івана Франка", "Лютеранська",
                "Сумська", "Проскурівська", "Харківська", "Богдана Хмельницького", "Львівська",
                "Грушевського", "Володимирська", "Театральна", "Паустовського"};
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (String streetName : streetNames) {
                preparedStatement.setString(1, streetName);
                preparedStatement.executeUpdate();
            }
        }
    }

    private void fillCityTable() throws SQLException {
        String sql = "INSERT INTO city (name) VALUES (?)";
        String[] cityNames = {"Київ", "Харків", "Одеса", "Дніпро", "Львів", "Запоріжжя", "Івано-Франківськ", "Херсон", "Чернівці", "Тернопіль"};
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (String cityName : cityNames) {
                preparedStatement.setString(1, cityName);
                preparedStatement.executeUpdate();
            }
        }
    }

    private void fillAddressTable() throws SQLException {
        String sql = "INSERT INTO address (city_id, street_id, number_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int[][] addressData = {
                    {1, 1, 1},
                    {2, 2, 2},
                    {3, 3, 3},
                    {4, 4, 4},
                    {5, 5, 5},
                    {1, 6, 6},
                    {2, 7, 7},
                    {3, 8, 8},
                    {4, 9, 9},
                    {5, 10, 10},
                    {6, 11, 11},
                    {7, 12, 12},
                    {8, 13, 13},
                    {9, 14, 14},
                    {10, 15, 15},
                    {6, 16, 16},
                    {7, 17, 17},
                    {8, 18, 18},
                    {9, 19, 19},
                    {10, 19, 20}
            };

            for (int[] data : addressData) {
                preparedStatement.setInt(1, data[0]);
                preparedStatement.setInt(2, data[1]);
                preparedStatement.setInt(3, data[2]);
                preparedStatement.executeUpdate();
            }
        }
    }

    private void fillShopTable() throws SQLException {
        String sql = "INSERT INTO shop (address_id) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int[] addressIds = {1, 6, 2, 3, 4, 5, 10, 11, 12, 13, 14, 15};

            for (int addressId : addressIds) {
                preparedStatement.setInt(1, addressId);
                preparedStatement.executeUpdate();
            }
        }
    }

    private void fillProductCategoryTable() throws SQLException {
        String sql = "INSERT INTO product_category (name) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            String[] categoryNames = {
//                    "М'які меблі",
//                    "Водопостачання та сантехніка",
//                    "Електрика",
//                    "Освітлення",
//                    "Будівельні матеріали",
//                    "Інструменти для ремонту",
//                    "Садові інструменти ",
//                    "Вікна",
//                    "Двері",
//                    "Покрівля",
//                    "Водостічні системи",
//                    "Фарби",
//                    "Фінішні матеріали",
//                    "Меблі для ванної кімнати",
//                    "Поли",
//                    "Покриття підлоги",
//                    "Декоративні елементи для дому",
//                    "Вентиляція та кондиціонування",
//                    "Будівельні інструменти",
//                    "Системи безпеки та захисту",
//                    "Системи опалення та вентиляції",
//                    "Системи зберігання та організації",
//                    "Матеріали для обробки стін та стелі",
//                    "Спортивні зони та обладнання",
//                    "Сучасні технології для дому",
//                    "Садові меблі та відпочинок",
//                    "Водяні фільтри та очисні системи",
//                    "Інтер'єрні рішення та декор",
//                    "Все для вирощування рослин",
//                    "Побутова техніка для кухні",
//                    "Відпочинок та дозвілля у дворі",
//                    "Офісні прилади",
//                    "Офісні меблі",
//                    "Системи автоматизації дому",
//                    "Інструменти для газону та саду",
//                    "Аксесуари для кухні та приладдя",
//                    "Безпека та захист для дому",
//                    "Прилади для прибирання та догляду",
//                    "Системи водопостачання і фільтрації",
//                    "Мистецтво та ремесла",
//                    "Світильники та освітлення",
//                    "Електроніка та сучасні технології",
//                    "Матеріали для майстерні та творчості",
//                    "Гараж та автомобільні принади",
//                    "Аптечка та системи першої допомоги",
//                    "Ліхтарі",
//                    "Меблі для спальні та відпочинку",
//                    "Комплекти для облаштування городу",
//                    "Весільні та святкові прикраси",
//                    "Книги та освітні матеріали для дому",
//                    "Товари для домашніх тварин",
//                    "Подарунки та сувеніри для дому",
//                    "Іграшки та розваги для всієї родини",
//                    "Меблі для гостьової кімнати",
//                    "Техніка для краси та здоров'я",
//                    "Вишивання та рукоділля для дому",
//                    "Чоловічі товари та приладдя",
//                    "Товари для пікніків та відпочинку",
//                    "Жіночі товари та аксесуари",
//                    "Товари для плавання та пляжу",
//                    "Робочі інструменти та аксесуари",
//                    "Малювання та мистецтво для дітей",
//                    "Господарські товари та приладдя",
//                    "Кавоварки та аксесуари для кави",
//                    "Посуд та кухонне обладнання",
//                    "Печі та каміни для дому",
//                    "Банкетки та табурети для кухні",
//                    "Товари для велоспорту та активного відпочинку",
//                    "Текстиль для дому та спальні",
//                    "Годинники та настільні годинники",
//                    "Ліжка",
//                    "матраци для спальні",
//                    "Ігри для всієї сім'ї",
//                    "Техніка для дому",
//                    "Товари для здоров'я",
//                    "Товари для фітнесу",
//                    "Кухонні прилади",
//                    "Товари для активного відпочинку",
//                    "Меблі для робочого простору",
//                    "Товари для готування та кулінарії",
//                    "іграшкові машини",
//                    "Матеріали для творчості та DIY",
//                    "Товари для приготування напоїв",
//                    "Товари для автолюбителів та гаражу",
//                    "Товари для гаражу",
//                    "Дерев'яні іграшки",
//                    "Дерев'яні головоломки",
//                    "Взуття для дому",
//                    "Садова техніка",
//                    "Побутова хімія",
//                    "засоби догляду",
//                    "навчальні матеріали",
//                    "Книжки",
//                    "Мобільні гаджети",
//                    "Мобільні аксесуари",
//                    "Товари для ремонту",
//                    "засоби безпеки",
//                    "Маски",
//                    "Іграшки для домашніх тварин",
//                    "Ігри на свіжому повітрі для дітей",
//                    "Чоловіча та жіноча мода",
//                    "Товари для студентів",
//                    "Товари для дому з екологічних матеріалів",
//                    "Дитячі ігри",
//                    "Килими для дому",
//                    "Доріжки для дому",
//                    "Літні аксесуари для дому",
//                    "зимові аксесуари для дому",
//                    "Товари для водних видів спорту",
//                    "Подарункові комплекти",
//                    "Подарункові набори",
//                    "Паперові товари",
//                    "Письмові товари",
//                    "Товари для офісу",
//                    "Товари для бізнесу",
//                    "Товари для рукоділля",
//                    "Товари для шиття",
//                    "Електронні ігри",
//                    "Електронні іграшки",
//                    "Товари для зберігання",
//                    "Товари для організації"
//            };
//            for (String categoryName : categoryNames) {
//                preparedStatement.setString(1, categoryName);
//                preparedStatement.executeUpdate();
//            }
            preparedStatement.setString(1, "М'які меблі");
            preparedStatement.executeUpdate();
            for (int i = 0; i < 1000; i++) {
                preparedStatement.setString(1, RandomStringUtils.randomAlphabetic(10));
                preparedStatement.executeUpdate();
            }
        }
    }
}
