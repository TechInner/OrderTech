//package com.techinner.TechInner.mapper.implementation_old;
//
//import com.techinner.TechInner.dto.request.MenuRequestDTO;
//import com.techinner.TechInner.dto.response.MenuResponseDTO;
//import com.techinner.TechInner.entity.Menu;
//
//public class MenuMapper {
//
// public static Menu toEntity(MenuRequestDTO dto){
//     if (dto == null) return null;
//     return Menu.builder()
//             .name(dto.getName())
//             .price(dto.getPrice())
//             .description(dto.getDescription())
//             .build();
// }
//
// public static MenuResponseDTO toResponse(Menu menu){
//     if (menu == null) return null;
//     return MenuResponseDTO.builder()
//             .id(menu.getId())
//             .name(menu.getName())
//             .price(menu.getPrice())
//             .description(menu.getDescription())
//             .build();
// }
//
// public static void updateEntityFromRequest(MenuRequestDTO dto, Menu existing){
//     if (dto.getName() != null) existing.setName(dto.getName());
//     if (dto.getPrice() != null) existing.setPrice(dto.getPrice());
//     if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
// }
//
//}
