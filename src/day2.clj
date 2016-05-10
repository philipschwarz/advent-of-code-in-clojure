(ns day2)

(require '[clojure.string :as str])

(def lines-with-3d-dimensions
  (line-seq (clojure.java.io/reader "src/day2-input.txt")))

(def dimension-separator #"x")

(defn parse-dimension [text]
  (Integer/parseInt text))

(defn extract-dimensions [line]
  (let [dimensions (str/split line dimension-separator)]
    (map parse-dimension dimensions)))

(def dimensions-of-presents
  (map extract-dimensions
       lines-with-3d-dimensions))

(defn paper-needed-to-wrap-present [[presentLength presentWidth presentHeight]]
  (let [areasOf3DifferentSidesOfPresent [(* presentLength presentWidth) (* presentLength presentHeight) (* presentWidth presentHeight)]
        areaOfSmallestSide (apply min areasOf3DifferentSidesOfPresent)
        totalAreaOf3DifferentSidesOfPresent (apply + areasOf3DifferentSidesOfPresent)
        totalAreaOfPresent (* 2 totalAreaOf3DifferentSidesOfPresent)]
    (+ totalAreaOfPresent areaOfSmallestSide)))

(def paper-needed-to-wrap-all-presents
  (reduce +
    (map paper-needed-to-wrap-present
         dimensions-of-presents)))

(def answer-1 paper-needed-to-wrap-all-presents)

(assert (= 1606483 answer-1))

(defn ribbon-needed-to-wrap-present [[length width height]]
  (let [semiperimetersOf3DifferentSidesOfPresent [(+ length width) (+ length height) (+ width height)]
        length-of-ribbon-for-smallest-perimeter (* 2 (apply min semiperimetersOf3DifferentSidesOfPresent))
        length-of-ribbon-for-bow (* length width height)]
    (+ length-of-ribbon-for-smallest-perimeter length-of-ribbon-for-bow)))

(def ribbon-needed-to-wrap-all-presents
  (reduce +
    (map ribbon-needed-to-wrap-present
         dimensions-of-presents)))

(def answer-2 ribbon-needed-to-wrap-all-presents)

(assert (= 3842356 answer-2))