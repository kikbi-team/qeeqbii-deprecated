package ch.epfl.sweng.qeeqbii;

/**
 * Created by davidcleres on 31.10.17.
 */

public class ModelForShoppingCart {

        private String m_name;
        private boolean m_selected;

        public ModelForShoppingCart(String name) {
            this.m_name = name;
            m_selected = false;
        }

        public String getName() {
            return m_name;
        }

        public void setName(String name) {
            this.m_name = name;
        }

        public boolean isSelected() {
            return m_selected;
        }

        public void setSelected(boolean selected) {
            this.m_selected = selected;
        }

    }
