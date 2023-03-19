package backendrest.powergrid.code;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErroPadrao> cpviException(RuntimeException e, HttpServletRequest request) {
		log.error("CPVIException: ", e);
		ErroPadrao err = new ErroPadrao(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "CpviException",
				e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@Data
	class ErroPadrao implements Serializable {
		private static final long serialVersionUID = 1L;

		private LocalDateTime ldt;
		private Integer status;
		private String error;
		private String message;
		private String path;

		public ErroPadrao(LocalDateTime ldt, Integer status, String error, String message, String path) {
			super();
			this.ldt = ldt;
			this.status = status;
			this.error = error;
			this.message = message;
			this.path = path;
		}
	}
}
