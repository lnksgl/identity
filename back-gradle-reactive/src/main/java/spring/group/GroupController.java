package spring.group;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    GroupService groupService;

    @PostMapping
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupService.createGroup(groupDto);
    }

    @PutMapping
    public void updateGroup(@RequestBody GroupDto groupDto) {
        groupService.updateGroup(groupDto);
    }

    @GetMapping("/name/{name}")
    public Mono<GroupDto> getUsernameGroups(@PathVariable String name) {
        return groupService.showNameGroup(name);
    }

    @GetMapping
    public Flux<GroupDto> showAllGroups() {
        return groupService.showAllGroups();
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping("{id}")
    public Mono<GroupDto> getSingleGroup(@PathVariable Long id) {
        return groupService.readSingleGroup(id);
    }
}
