(ns rubyconf-2016.cards
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs.test :as t :refer [report] :include-macros true]
            [rubyconf-2016.todo :as todo]
            [devcards.core :as dc]))

(dc/defcard-rg todo-item
  [todo/todo-item [1 {:title "Olar"}]]
  todo/todos
  {:inspect-data true})

(dc/defcard-rg todo-list
  [todo/todo-list]
  todo/todos
  {:inspect-data true})

(dc/defcard-rg todo-input
  [todo/todo-input]
  todo/todos
  {:inspect-data true :history true})

(dc/deftest first-testers
  (t/testing "Teste simples"
    (t/is (= (+ 3 2) 5) "testando adição")))

(reagent/render [:div] (.getElementById js/document "app"))

;; remember to run 'lein figwheel devcards' and then browse to
;; http://localhost:3449/cards
