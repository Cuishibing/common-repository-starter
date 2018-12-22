package cui.shibing.commonrepository;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

public class Specifications {
    public static <T, V extends Comparable<V>> Specification<T> equal(String name, V value) {
        return (Specification<T>) (root, query, criteriaBuilder) -> {
            Path<V> path = root.get(name);
            return criteriaBuilder.equal(path, value);
        };
    }

    public static <T, V extends CharSequence> Specification<T> like(String name, V value) {
        return (Specification<T>) (root, query, criteriaBuilder) -> {
            Path<String> path = root.get(name);
            return criteriaBuilder.like(path,value.toString());
        };
    }

    public static <T, V extends CharSequence> Specification<T> notLike(String name, V value) {
        return (Specification<T>) (root, query, criteriaBuilder) -> {
            Path<String> path = root.get(name);
            return criteriaBuilder.notLike(path,value.toString());
        };
    }

    public static <T, V extends Comparable<V>> Specification<T> lessThan(String name, V value) {
        return (Specification<T>)(root, query, criteriaBuilder) -> {
            Path<V> path = root.get(name);
            return criteriaBuilder.lessThan(path, value);
        };
    }

    public static <T, V extends Comparable<V>> Specification<T> lessThanOrEqualTo(String name, V value) {
        return (Specification<T>)(root, query, criteriaBuilder) -> {
            Path<V> path = root.get(name);
            return criteriaBuilder.lessThanOrEqualTo(path, value);
        };
    }

    public static <T, V extends Comparable<V>> Specification<T> greaterThan(String name, V value) {
        return (Specification<T>)(root, query, criteriaBuilder) -> {
            Path<V> path = root.get(name);
            return criteriaBuilder.greaterThan(path, value);
        };
    }

    public static <T, V extends Comparable<V>> Specification<T> greaterThanOrEqualTo(String name, V value) {
        return (Specification<T>)(root, query, criteriaBuilder) -> {
            Path<V> path = root.get(name);
            return criteriaBuilder.greaterThanOrEqualTo(path, value);
        };
    }
}
