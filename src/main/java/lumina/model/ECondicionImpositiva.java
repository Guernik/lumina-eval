package lumina.model;

public enum ECondicionImpositiva {
	
	RESPONSABLE_INSCRIPTO {

		@Override
		public Integer codigo() {
			return 1;
		}

		@Override
		public String letra() {
			return "A";
		}

		@Override
		public Float porcentaje() {
			return 0.1005f;
		}
		
	},
	MONOTRIBUTO{

		@Override
		public Integer codigo() {
			return 2;
		}
		@Override
		public String letra() {
			return "B";
		}
		@Override
		public Float porcentaje() {
			return 0.21f;
		}
		
	},
	NO_RESPONSABLE{

		@Override
		public Integer codigo() {
			return 3;
		}

		@Override
		public String letra() {
			// TODO Auto-generated method stub
			return "X";
		}

		@Override
		public Float porcentaje() {
			// TODO Auto-generated method stub
			return 0.7f;
		}
		
	}
	;
		
	
	public abstract Integer codigo();
	public abstract String letra();
	public abstract Float porcentaje();
	
}
