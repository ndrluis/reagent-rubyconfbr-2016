(ns rubyconf-2016.todo
 (:require [reagent.core :as reagent :refer [atom]]
           [clojure.string :as string]))

(defonce todos-counter (atom 0))
(defonce todos (atom (sorted-map)))

(defn add-todo [title]
  (let [id (swap! todos-counter inc)]
    (swap! todos assoc-in [id :title ] title)))

(defn delete-todo [id]
  (swap! todos dissoc id))

(defn todo-input []
 (let [value (atom "")]
   (fn []
     [:div
       [:input {:type "text"
                :value @value
                :on-change #(reset! value (-> % .-target .-value))}]
       [:input {:type "button"
                :value "Adicionar"
                :on-click #((do
                              (add-todo @value)
                              (reset! value "")))}]])))

(defn todo-item [[id {:keys [title]}]]
  [:li title
   [:input {:on-click #(delete-todo id)
            :type "button"
            :value "Remover"}]])

(defn todo-list []
  [:ul
   (for [todo @todos]
    ^{:key (first todo)}
    [todo-item todo])])

(defn todo-app []
  [:div
   [todo-input]
   [todo-list]])

