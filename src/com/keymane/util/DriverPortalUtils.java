package com.keymane.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DriverPortalUtils {
    private static final int DECIMAL_PLACES = 2;
    public static String RANGE_DELIMITER = "-";

    public enum ORDER {
        ASC, DESC
    }

    private DriverPortalUtils() {
    }

    public static Short getShort(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                Short Id = Short.parseShort(obj.toString());
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getShort " + e.getMessage());
            return null;
        }

    }

    public static Integer getInt(Object obj) {
        try {
            if (obj == null) {
                return 0;
            } else {
                Integer Id = Integer.parseInt(obj.toString());
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getInt " + e.getMessage());
            return null;
        }

    }

    public static Double getDouble(Object obj) {
        try {
            if (obj == null) {
                return new Double(0);
            } else {
                Double Id = Double.parseDouble(obj.toString());
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getInt " + e.getMessage());
            return null;
        }

    }

    /**
     * Get Integer value. If the value is null or has an error, the default value is returned.
     * 
     * @param obj
     * @param defaultValue
     * @return value
     */
    public static Integer getInt(Object obj, Integer defaultValue) {
        Integer value = getIntOrNull(obj);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static Integer getIntOrNull(Object obj) {
        try {
            Integer id = null;
            if (obj != null) {
                id = Integer.parseInt(obj.toString());
            }

            return id;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer getIntOrError(Object obj) {
        Integer id = null;
        if (obj != null) {
            id = Integer.parseInt(obj.toString());
        }

        return id;
    }

    public static Float getFloat(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                Float Id = Float.parseFloat(obj.toString());
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getInt " + e.getMessage());
            return null;
        }

    }

    public static Long getLong(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                Double temp = Double.parseDouble(obj.toString());
                Long Id = temp.longValue();
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getLong " + e.getMessage());
            return null;
        }
    }

    public static Long getLong(Object obj, Long defaultReturn) {
        try {
            if (obj == null) {
                return defaultReturn;
            } else {
                Double temp = Double.parseDouble(obj.toString());
                Long Id = temp.longValue();
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getLong " + e.getMessage());
            return defaultReturn;
        }
    }

    public static Long getLongOrError(Object obj) throws Exception {
        if (obj == null) {
            throw new Exception("The value is null");
        } else {
            Double temp = Double.parseDouble(obj.toString());
            Long Id = temp.longValue();
            return Id;
        }
    }

    public static String getStrings(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                String temp = String.valueOf(obj.toString());
                return temp;
            }
        } catch (Exception e) {

            // logger.debug("Error:getLong " + e.getMessage());
            return null;
        }
    }

    public static String getString(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                String id = obj.toString();
                return id;

            }
        } catch (Exception e) {

            return null;
        }
    }

    public static BigDecimal getBigDecimal(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                BigDecimal Id = BigDecimal.valueOf(Double.parseDouble(obj.toString()));
                return Id;
            }
        } catch (Exception e) {

            // logger.debug("Error:getBigDecimal " + e.getMessage());
            return null;
        }
    }

    public static Boolean getBoolean(Object obj) {
        try {
            if (obj == null) {
                return null;
            } else {
                if (obj.toString().equalsIgnoreCase("yes")) {
                    return true;
                } else if (obj.toString().equalsIgnoreCase("no")) {
                    return false;
                } else {
                    Boolean Id = Boolean.valueOf(obj.toString());
                    return Id;
                }
            }
        } catch (Exception e) {

            // logger.debug("Error:getBigDecimal " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a list of parameters with field, operator and value maps.
     * 
     * @param parameters
     * @return parameters
     */
    public static List<Map<String, Object>> extractFilterParams(Map<String, Map<String, Object>> parameters) {

        if (parameters == null) {
            return null;
        }
        List<Map<String, Object>> filterParams = new ArrayList<Map<String, Object>>();
        Map<String, Object> aParam;

        Iterator<Map.Entry<String, Map<String, Object>>> it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Map<String, Object>> entry = it.next();
            Map<String, Object> valueMap = entry.getValue();
            String col = entry.getKey();

            Set<String> operands = valueMap.keySet();
            for (String operand : operands) {
                aParam = new HashMap<String, Object>();

                aParam.put("fieldName", col);
                aParam.put("operator", operand);
                aParam.put("value", valueMap.get(operand));

                filterParams.add(aParam);
            }
        }
        return filterParams;

    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getSearchParams(String[] searchParams, Map<String, Object> criteria) {
        Map<String, Object> criteriaValues = new HashMap<String, Object>();
        if (searchParams != null && searchParams.length > 0 && criteria != null && criteria.size() > 0) {
            for (int i = 0; i < searchParams.length; i++) {
                if (criteria.containsKey(searchParams[i])) {
                    Map<String, Object> searchColumn = (Map<String, Object>) criteria.get(searchParams[i]);
                    if (searchColumn.containsKey("eq")) {
                        criteriaValues.put(searchParams[i], searchColumn.get("eq"));
                    }
                    if (searchColumn.containsKey("gt")) {
                        criteriaValues.put(searchParams[i] + "_start", searchColumn.get("gt"));
                    }
                    if (searchColumn.containsKey("lt")) {
                        criteriaValues.put(searchParams[i] + "_end", searchColumn.get("lt"));
                    }

                }
            }

        }
        return criteriaValues;
    }

    @SuppressWarnings("unchecked")
    public static Object getParameterValue(String searchKey, String operator, Map<String, Object> parameters) {
        if (searchKey != null && !searchKey.isEmpty() && parameters != null) {
            Map<String, Object> params = (Map<String, Object>) parameters.get(searchKey);
            if (params != null && params.containsKey(operator)) {
                return params.get(operator);
            }

        }
        return null;
    }

    /**
     * Note :It divides by 100 - assuming cents are input
     * 
     * @param value
     * @return
     */
    public static String toCurrency(Object value) {
        BigDecimal currency = getBigDecimal(value);
        if (currency != null) {
            currency.setScale(DECIMAL_PLACES, BigDecimal.ROUND_UNNECESSARY);
            return currency.divide(BigDecimal.valueOf(100l)).setScale(2).toString();
        }
        return BigDecimal.ZERO.setScale(2).toString();
    }

    public static <T> Collection<?> getSafeCollection(Collection<T> c) {
        return c == null ? Collections.emptyList() : c;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> safeList(List<T> c) {
        return (List<T>) (c == null ? Collections.emptyList() : c);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map sortByComparator(Map map) {

        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * 
     * @param map
     * @param sortOrder
     * @return sorted Map
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map sortByComparatorByKey(Map map, final ORDER sortOrder) {

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {

                if (sortOrder == ORDER.DESC) {
                    return ((Comparable) ((Map.Entry) (o2)).getKey()).compareTo(((Map.Entry) (o1)).getKey());
                } else {
                    return ((Comparable) ((Map.Entry) (o1)).getKey()).compareTo(((Map.Entry) (o2)).getKey());
                }
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static String formatPriceRange(String strPrice) {
        if (strPrice == null || strPrice.isEmpty()) {
            return null;
        }
        if (strPrice.startsWith(RANGE_DELIMITER)) {
            strPrice = "* TO " + strPrice.substring(strPrice.indexOf(RANGE_DELIMITER) + 1).trim();
        } else if (strPrice.endsWith(RANGE_DELIMITER)) {
            strPrice = strPrice.substring(0, strPrice.indexOf(RANGE_DELIMITER)).trim() + " TO *";
        } else {
            strPrice = strPrice.replace(RANGE_DELIMITER, " TO ");
        }
        return "[" + strPrice + "]";
    }

    public static String round(Object unrounded, int precision) {
        try {

            BigDecimal bd = new BigDecimal(String.valueOf(unrounded));
            BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
            return rounded.toPlainString();

        } catch (Exception e) {
            return String.valueOf(unrounded);
        }
    }

    /**
     * generate a random string given limots
     * 
     * @param minLength
     * @param maxLength
     * @return
     */
    public static String getRandomString(int minLength, int maxLength) {

        String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // special

        int charsetlength = charset.length();

        String result = "";

        Random rand = new Random(new Date().getTime());

        int length = minLength + (rand.nextInt(maxLength));

        for (int i = 0; i < length; i++)
            result += charset.charAt(rand.nextInt(charsetlength));

        return result;
    }

    //

    public static String getSortKey(String sortKeyOrder) {
        String sortKey = sortKeyOrder != null && !sortKeyOrder.isEmpty() && sortKeyOrder.contains("-") ? sortKeyOrder.substring(0, sortKeyOrder.indexOf("-"))
                : "score";
        return sortKey;
    }

    public static String getSortOrder(String sortKeyOrder) {
        String sortOrder = sortKeyOrder != null && !sortKeyOrder.isEmpty() ? sortKeyOrder.substring((sortKeyOrder.indexOf("-") + 1)) : "asc";
        return sortOrder;
    }

    public static List<Long> getLongList(Object obj, String regex) {
        List<Long> longList = new ArrayList<Long>();
        try {
            if (obj == null || regex == null) {
                return longList;
            } else {
                String objString = getString(obj);
                if (objString == null) {
                    return longList;
                }

                String[] numbers = objString.split(regex);
                for (String a : numbers) {
                    Long num = getLong(a);
                    if (num != null) {
                        longList.add(num);
                    }
                }

                return longList;
            }
        } catch (Exception e) {

            // logger.debug("Error:getLong " + e.getMessage());
            return longList;
        }
    }

    public static List<Long> stringToLongList(List<String> stringList) {
        List<Long> longList = new ArrayList<Long>(stringList.size());
        try {

            for (String val : stringList) {
                Long longVal = getLong(val);
                if (longVal != null) {
                    longList.add(longVal);
                }
            }

            return longList;

        } catch (Exception e) {

            return longList;
        }
    }

    public static String escapeSolrQuery(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{'
                    || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&' || c == ';') {
                sb.append('\\');
            }

            sb.append(c);
        }
        return sb.toString();
    }

    public static String escapeSolrResult(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{'
                    || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&' || c == ';') {
                sb.append("");
            } else {

                sb.append(c);
            }
        }
        return sb.toString().trim();
    }

    public static String ordinalNumber(Integer number) {
        if (number >= 11 && number <= 13) {
            return number + "th";
        }
        switch (number % 10) {
        case 1:
            return number + "st";
        case 2:
            return number + "nd";
        case 3:
            return number + "rd";
        default:
            return number + "th";
        }
    }

    public enum FeedEnum {
        _POST_PRODUCT_DATA_("_POST_PRODUCT_DATA_"), _POST_PRODUCT_RELATIONSHIP_DATA_("_POST_PRODUCT_RELATIONSHIP_DATA_"), _POST_PRODUCT_IMAGE_DATA_(
                "_POST_PRODUCT_IMAGE_DATA_"), _POST_INVENTORY_AVAILABILITY_DATA_("_POST_INVENTORY_AVAILABILITY_DATA_"), _POST_PRODUCT_PRICING_DATA_(
                "_POST_PRODUCT_PRICING_DATA_");
        private String feed;

        FeedEnum(String s) {
            feed = s;
        }

        public String toString() {
            return feed;
        }
    }

    public enum StatusEnum {
        PRE_PROCESSING("PRE-PROCESSING"), PROCESSING_ERROR("PROCESSING-ERROR"), POST_PROCESSING("POST-PROCESSING"), _AWAITING_ASYNCHRONOUS_REPLY_(
                "_AWAITING_ASYNCHRONOUS_REPLY_"), _CANCELLED_("_CANCELLED_"), _DONE_("_DONE_"), _IN_PROGRESS_("_IN_PROGRESS_"), _IN_SAFETY_NET_(
                "_IN_SAFETY_NET_"), _SUBMITTED_("_SUBMITTED_"), _UNCONFIRMED_("_UNCONFIRMED_");

        private String status;

        StatusEnum(String s) {
            status = s;
        }

        public String toString() {
            return status;
        }
    }
}
