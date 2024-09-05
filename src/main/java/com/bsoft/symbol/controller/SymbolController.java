package com.bsoft.symbol.controller;

import com.bsoft.symbol.exception.Problem;
import com.bsoft.symbol.model.Symbol;
import com.bsoft.symbol.service.SymbolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("http://localhost:4200")
public class SymbolController {
    private final int SYMBOL_LIMIT = 10;
    private final SymbolService symbolService;

    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }


    /**
     * GET /symbols/name/{name}
     *
     * @param name (required)
     * @return Resultaat van symbol bevraging (status code 200)
     * or Not found (status code 404)
     * or Unexpected problem (status code 500)
     */
    @Operation(
            operationId = "getSymbol",
            tags = {"Symbol"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultaat van symbol bevraging", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Symbol.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Unexpected problem", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "api_key")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/symbols/name/{name}",
            produces = {"application/json", "application/problem+json"}
    )
    public ResponseEntity<?> getSymbol(@PathVariable("name") String name) {
        log.info("Start get: {}", name);
        Symbol symbol = symbolService.findSymbol(name);

        try {
            if (symbol != null) {
                return ResponseEntity.ok(symbol);
            } else {
                Problem problem = new Problem();
                problem.setStatus(404);
                problem.setDetail("Symbol: " + name + " not found");
                return new ResponseEntity<Problem>(problem, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Problem problem = new Problem();
            problem.setStatus(500);
            problem.setDetail("Unknown error");
            problem.setDetail(e.getMessage());
            return new ResponseEntity<Problem>(problem, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * GET /symbols/{id}
     *
     * @param id (required)
     * @return Resultaat van symbol bevraging (status code 200)
     * or Not found (status code 404)
     * or Unexpected problem (status code 500)
     */
    @Operation(
            operationId = "getSymbolById",
            tags = {"Symbol"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultaat van symbol bevraging", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Symbol.class)),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Unexpected problem", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "api_key")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/symbols/{id}",
            produces = {"application/json", "application/problem+json"}
    )
    public ResponseEntity<?> getSymbolById(@PathVariable("id") Long id) {
        log.info("Start get by id: {}", id);
        Symbol symbol = symbolService.findSymbolById(id);

        try {
            if (symbol != null) {
                return ResponseEntity.ok(symbol);
            } else {
                Problem problem = new Problem();
                problem.setStatus(404);
                problem.setDetail("Symbol with: " + id + " not found");
                return new ResponseEntity<Problem>(problem, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Problem problem = new Problem();
            problem.setStatus(500);
            problem.setDetail("Unknown error");
            problem.setDetail(e.getMessage());
            return new ResponseEntity<Problem>(problem, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * GET /symbols/lookup/{name}
     *
     * @param name (required)
     * @return Resultaat van symbol bevraging (status code 200)
     * or Not found (status code 404)
     * or Unexpected problem (status code 500)
     */
    @Operation(
            operationId = "lookupSymbol",
            tags = {"Symbol"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultaat van symbol bevraging", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Symbol.class))),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Unexpected problem", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "api_key")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/symbols/lookup/{name}",
            produces = {"application/json", "application/problem+json"}
    )
    public ResponseEntity<?> lookupSymbol(@PathVariable("name") String name, @RequestParam(required = false, name = "limit") String limit) {
        log.info("Lookup: {} limit: {}", name, limit);
        List<Symbol> symbol = new ArrayList<Symbol>();
        int symbol_limit = 0;
        if (limit != null) {
            try {
                symbol_limit = Integer.parseInt(limit);
            } catch (NumberFormatException e) {
                log.error("Invalid limit: {}, using default {}", limit, SYMBOL_LIMIT);
                symbol_limit = SYMBOL_LIMIT;
            }
        } else {
            symbol_limit = SYMBOL_LIMIT;
        }
        try {
            symbol = symbolService.lookupSymbol(name, symbol_limit);
            return ResponseEntity.ok(symbol);
        } catch (Exception e) {
            Problem problem = new Problem();
            problem.setStatus(500);
            problem.setDetail("Unknown error");
            problem.setDetail(e.getMessage());
            return new ResponseEntity<Problem>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * GET /symbols/prefix
     *
     * @return Resultaat van symbol bevraging (status code 200)
     * or Not found (status code 404)
     * or Unexpected problem (status code 500)
     */
    @Operation(
            operationId = "prefixSymbol",
            tags = {"Symbol"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultaat van symbol bevraging", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Symbol.class))),
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not found", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    }),
                    @ApiResponse(responseCode = "default", description = "Unexpected problem", content = {
                            @Content(mediaType = "application/problem+json", schema = @Schema(implementation = Problem.class))
                    })
            },
            security = {
                    @SecurityRequirement(name = "api_key")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/symbols/prefix",
            produces = {"application/json", "application/problem+json"}
    )
    public ResponseEntity<?> prefixSymbol() {
        log.info("prefix symbol");
        List<String> prefix = new ArrayList<String>();
        try {
            prefix = symbolService.prefixSymbol();
            return ResponseEntity.ok(prefix);
        } catch (Exception e) {
            Problem problem = new Problem();
            problem.setStatus(500);
            problem.setDetail("Unknown error");
            problem.setDetail(e.getMessage());
            return new ResponseEntity<Problem>(problem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
