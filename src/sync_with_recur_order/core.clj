(ns sync-with-recur-order.core
  (:require [clojure.data.json :as json]))

(defn sort-recurs
  "sort recurrence"
  ([recs] (reverse (sort-by #(get-in % ["timeStart" "$date"]) recs))))

(defn sorted-recur-time
  "sort recurrence with time of a fair"
  ([fair] (update fair "recurrence" sort-recurs)))

(with-open [rdr (clojure.java.io/reader
                 "/home/leo/docs/website/v3/devOps/m2es/f100.json")]
  (let [lines (line-seq rdr)
        fairs (doall (map json/read-str lines))
        sorted-fairs (map sorted-recur-time fairs)]
    (with-open [w (clojure.java.io/writer
                   "/home/leo/docs/website/v3/devOps/m2es/f100s.json")]
      (json/write sorted-fairs w :escape-unicode false))))

(defn -main [& args]
  (with-open [in (clojure.java.io/reader "./resources/fairs.json")
              out (clojure.java.io/writer "./resources/fairsSorted.json")]
    (json/write (map sorted-recur-time
                     (doall (map json/read-str
                                 (line-seq in))))
                out
                :escape-unicode false)))
