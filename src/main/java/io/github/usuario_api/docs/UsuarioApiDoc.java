package io.github.usuario_api.docs;

import io.github.usuario_api.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Usuários", description = "Operações de gerenciamento de usuários")
public interface UsuarioApiDoc {

    @Operation(summary = "Cadastrar novo usuário",
            description = "Cria um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
    })
    ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(
            @Parameter(description = "Dados do usuário para cadastro") UsuarioDTO dto);

    @Operation(summary = "Listar todos os usuários",
            description = "Retorna uma lista com todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class)))
    })
    ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios();

    @Operation(summary = "Autenticar usuário",
            description = "Realiza login de um usuário com email e senha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content)
    })
    ResponseEntity<LoginResponseDTO> autenticar(
            @Parameter(description = "Dados de login (email e senha)") LoginRequestDTO dto);

    @Operation(summary = "Atualizar dados do usuário",
            description = "Atualiza informações de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateUsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<UpdateUsuarioResponseDTO> atualizarUsuario(
            @Parameter(description = "ID do usuário") Long id,
            @Parameter(description = "Novos dados do usuário") UpdateUsuarioRequestDTO dto);

    @Operation(summary = "Atualizar endereço do usuário",
            description = "Atualiza o endereço do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UpdateResponseEnderecoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<UpdateResponseEnderecoDTO> atualizarEndereco(
            @Parameter(description = "ID do usuário") Long id,
            @Parameter(description = "Novos dados do endereço") EnderecoDTO dto);

    @Operation(summary = "Alterar senha do usuário",
            description = "Permite alterar a senha do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<Void> alterarSenha(
            @Parameter(description = "ID do usuário") Long id,
            @Parameter(description = "Nova senha do usuário") UsuarioSenhaUpdateDTO dto);

    @Operation(summary = "Logout do usuário",
            description = "Realiza o logout do usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Logout realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<Void> logout(
            @Parameter(description = "ID do usuário") Long id);

    @Operation(summary = "Excluir usuário",
            description = "Exclui um usuário do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<Void> excluir(
            @Parameter(description = "ID do usuário") Long id);

    @Operation(summary = "Buscar usuário por ID",
            description = "Busca os detalhes de um usuário pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @Parameter(description = "ID do usuário") Long id);
}
