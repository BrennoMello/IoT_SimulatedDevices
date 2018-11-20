import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;


public class VirtualSensor {
	private String name;
	private String typeValue;
        private String statisticalDistribution;
        private List<Object> values;
	private VirtualDevice device;
	private String fileSytheticDataSet;
        private Instances dataSet;
        private int qtdData;
        
	public VirtualSensor(String name, String typeValue, List<Object> values, VirtualDevice device) {
		this.name = name;
		this.typeValue = typeValue;
		this.values = values;
		this.device = device;
	}
        
        public VirtualSensor(String name, String typeValue, VirtualDevice device, String statisticalDistribution, String fileSytheticDataSet) {
		this.name = name;
		this.typeValue = typeValue;
		this.values = values;
		this.device = device;
                this.fileSytheticDataSet = fileSytheticDataSet;
                this.statisticalDistribution = statisticalDistribution;
                openDataSet();
	}
	
	public VirtualSensor(String name, String typeValue, VirtualDevice device, String statisticalDistribution) {
		this.name = name;
		this.typeValue = typeValue;
		this.device = device;
                this.statisticalDistribution = statisticalDistribution;
	}
        
        public void openDataSet(){
            Instances instances = null;
            try{
            // Read the instances from a file.
            FileReader reader = new FileReader(this.fileSytheticDataSet);
            instances = new Instances(reader);
            /*
            // Get the relation name.
            System.out.println(instances.relationName());
            // Get the number of attributes.
            System.out.println(instances.numAttributes()+" attributes");
            // Show the attributes.
            for(int i=0;i<instances.numAttributes();i++){
                
                String name = instances.attribute(i).name();
                int type = instances.attribute(i).type();
                String typeName = "";
                switch(type){
                    case Attribute.NUMERIC: typeName = "Numeric"; break;
                    case Attribute.NOMINAL: typeName = "Nominal"; break;
                    case Attribute.STRING: typeName = "String"; break;
                }
                System.out.println(name+" type "+typeName);
            }
            
            // Show the data.
            for(int i=0;i<instances.numInstances();i++){
                Instance instance = instances.instance(i); // ugh
                System.out.println((i+1)+": "+instance+ " missing? "+instance.hasMissingValue());
            }
            */
            
            }catch(IOException e){
                System.out.println("Error paser arff archive");
            }
            
            this.setDataSet(instances);
        }
	
        public double[] nextDataSimulatingConceptDrift(int amount){
                       
            double result[] = new double[amount];
            
            for (int i = 0; i < amount; i++) {
                this.setQtdData(this.getQtdData() + 1);
                //result[i] =  this.stream.nextInstance().getData().value(i);
                //System.out.println("Data: " + this.dataSet.instance(this.qtdData).value(1));
                result[i] =  this.getDataSet().instance(this.getQtdData()).value(1);
            }
            
            return result;
        }
        
        
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}

	public VirtualDevice getDevice() {
		return device;
	}

	public void setDevice(VirtualDevice device) {
		this.device = device;
	}

        /**
         * @return the statisticalDistribution
         */
        public String getStatisticalDistribution() {
            return statisticalDistribution;
        }

        /**
         * @param statisticalDistribution the statisticalDistribution to set
         */
        public void setStatisticalDistribution(String statisticalDistribution) {
            this.statisticalDistribution = statisticalDistribution;
        }

        /**
         * @return the fileSytheticDataSet
         */
        public String getFileSytheticDataSet() {
            return fileSytheticDataSet;
        }

        /**
         * @param fileSytheticDataSet the fileSytheticDataSet to set
         */
        public void setFileSytheticDataSet(String fileSytheticDataSet) {
            this.fileSytheticDataSet = fileSytheticDataSet;
        }

        /**
         * @return the dataSet
         */
        public Instances getDataSet() {
            return dataSet;
        }

        /**
         * @param dataSet the dataSet to set
         */
        public void setDataSet(Instances dataSet) {
            this.dataSet = dataSet;
        }

        /**
         * @return the qtdData
         */
        public int getQtdData() {
            return qtdData;
        }

        /**
         * @param qtdData the qtdData to set
         */
        public void setQtdData(int qtdData) {
            this.qtdData = qtdData;
        }
}
