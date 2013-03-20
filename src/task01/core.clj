(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn traverse [node]
  (if (= "r" (:class (attributes node)))
    (:href 
      (attributes 
        (first 
          (filter #(= :a (tag %)) (children node)))))
    (flatten 
      (map traverse 
        (filter #(vector? %) 
          (children node))))))

(defn get-links []
  (let [data (parse "clojure_google.html")]
    (vec (traverse data))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
