package service.functionnal.pann.domain;

import java.io.Serializable;
    public class SolarPanelValue implements Serializable {
        private int id;
        private String panel_name;
        private int panel_energy;
        private float panel_surface;
        private String label;
        private String panel_type;

        public SolarPanelValue(int id, String panel_name, int panel_energy, float panel_surface, String label, String panel_type) {
            this.id = id;
            this.panel_name = panel_name;
            this.panel_energy = panel_energy;
            this.panel_surface = panel_surface;
            this.label = label;
            this.panel_type = panel_type;
        }

        public SolarPanelValue() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPanel_name() {
            return panel_name;
        }

        public void setPanel_name(String panel_name) {
            this.panel_name = panel_name;
        }

        public int getPanel_energy() {
            return panel_energy;
        }

        public void setPanel_energy(int panel_energy) {
            this.panel_energy = panel_energy;
        }

        public float getPanel_surface() {
            return panel_surface;
        }

        public void setPanel_surface(float panel_surface) {
            this.panel_surface = panel_surface;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getPanel_type() {
            return panel_type;
        }

        public void setPanel_type(String panel_type) {
            this.panel_type = panel_type;
        }
    }
